package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Curriculum;
import com.revature.beans.CurriculumSubtopic;
import com.revature.beans.Schedule;
import com.revature.beans.remote.Subtopic;
import com.revature.exceptions.BadRequestException;
import com.revature.exceptions.NoContentException;
import com.revature.repositories.CurriculumRepository;
import com.revature.repositories.CurriculumSubtopicRepository;
import com.revature.repositories.ScheduleRepository;
import com.revature.util.ReflectionUtils;

/**
 * A Service class for retrieving and modifying curriculum data.
 * 
 * @author Ricky Baker (1802-Matt)
 */
@Service("curriculumService")
public class CurriculumService {
    private CurriculumRepository curriculumRepository;
    private CurriculumSubtopicRepository curriculumSubtopicRepository;
    private ScheduleRepository scheduleRepository;
    private RemoteTopicService remoteTopicService;
    private ScheduleService scheduleService;

    @Autowired
    public CurriculumService(CurriculumRepository curriculumRepository,
                    CurriculumSubtopicRepository curriculumSubtopicRepository,
                    ScheduleRepository scheduleRepository,
                    RemoteTopicService remoteTopicService,
                    ScheduleService scheduleService) {
        super();
        this.curriculumRepository = curriculumRepository;
        this.curriculumSubtopicRepository = curriculumSubtopicRepository;
        this.remoteTopicService = remoteTopicService;
        this.scheduleService = scheduleService;
    }

    /**
     * Returns all curriculums.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @return A list of all the curriculums in the database.
     */
    public List<Curriculum> getAllCurriculums() throws NoContentException {
        List<Curriculum> curriculumList = curriculumRepository.findAll();

        if (curriculumList != null && !curriculumList.isEmpty()) {
            return curriculumList;
        } else {
            throw new NoContentException("No curriculums found");
        }
    }

    /**
     * Find a curriculum by id.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param id
     *            The ID of the curriculum to find.
     * @return The curriculum with the given ID if it exists; otherwise,
     *         {@literal null} is returned.
     */
    public Curriculum getCurriculumById(Integer id) throws NoContentException {
        Curriculum curriculum = curriculumRepository.findCurriculumById(id);

        if (curriculum != null) {
            return curriculum;
        } else {
            throw new NoContentException(
                            "Curriculum by id: " + id + " was not found");
        }
    }

    /**
     * Finds all curriculums with the same name.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param name
     *            The curriculums' name.
     * 
     * @return A list of curriculums with the given name.
     */
    public List<Curriculum> findAllCurriculumByName(String name) {
        return curriculumRepository.findCurriculumByName(name);
    }

    /**
     * Acquire all subtopics from the topic service that belong to the given
     * curriculum.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculumId
     *            The ID of the curriculum.
     * 
     * @return A list of all subtopics that belong to the service.
     * 
     * @throws NoContentException
     *             No subtopics found.
     */
    public List<Subtopic> getAllSubtopicsForCurriculum(int curriculumId)
                    throws NoContentException {
        List<CurriculumSubtopic> curriculumSubtopics = curriculumSubtopicRepository
                        .findAllByCurriculumId(curriculumId);

        if (curriculumSubtopics == null || curriculumSubtopics.isEmpty()) {
            throw new NoContentException("No subtopics found.");
        }

        Set<Integer> subtopicIds = new TreeSet<>();

        curriculumSubtopics.forEach(currSubtopic -> {
            subtopicIds.add(currSubtopic.getSubtopicId());
        });

        List<Subtopic> subtopics = remoteTopicService
                        .requestSubtopics(subtopicIds);

        if (subtopics == null || subtopics.isEmpty()) {
            throw new NoContentException("No subtopics found.");
        }

        return subtopics;
    }

    /**
     * Get all subtopic IDs associated with the specified curriculum.
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculumId
     *            The ID of the associated curriculum.
     * @return The subtopic IDs associated with the given curriculum.
     * @throws NoContentException
     *             The curriculum has no associated subtopics.
     */
    public List<Integer> getAllSubtopicIdsForCurriculum(int curriculumId)
                    throws NoContentException {
        List<CurriculumSubtopic> curriculumSubtopics = curriculumSubtopicRepository
                        .findAllByCurriculumId(curriculumId);

        if (curriculumSubtopics == null || curriculumSubtopics.isEmpty()) {
            throw new NoContentException("No subtopics found.");
        }

        Set<Integer> subtopicIds = new TreeSet<>();

        curriculumSubtopics.forEach(currSubtopic -> {
            subtopicIds.add(currSubtopic.getSubtopicId());
        });

        List<Integer> subtopicIdList = new ArrayList<>(subtopicIds);

        if (subtopicIdList.isEmpty()) {
            throw new NoContentException("No subtopics found.");
        }

        return subtopicIdList;
    }

    /**
     * Mark the specified curriculum as the master version.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param id
     *            The ID of the curriculum.
     * @return The curriculum marked as master.
     * @throws BadRequestException
     *             The specified curriculum does not exist.
     */
    @Transactional
    public Curriculum markCurriculumAsMaster(int id)
                    throws BadRequestException {
        Curriculum targetCurriculum = null;

        try {
            targetCurriculum = getCurriculumById(id);
        } catch (NoContentException ex) {
        }

        if (targetCurriculum == null) {
            throw new BadRequestException(
                            "Curriculum with ID=" + id + " does not exist.");
        }

        List<Curriculum> curriculumList = curriculumRepository
                        .findAllCurriculumsByNameAndMasterVersion(
                                        targetCurriculum.getName(), true);

        if (curriculumList != null && !curriculumList.isEmpty()) {
            curriculumList.forEach(masterCurriculum -> {
                masterCurriculum.setIsMasterVersion(false);
            });
            curriculumRepository.save(curriculumList);
        }

        targetCurriculum.setIsMasterVersion(true);
        curriculumRepository.save(targetCurriculum);

        return targetCurriculum;
    }

    /**
     * Add a curriculum.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculum
     *            The curriculum to add.
     * @return The added curriculum.
     */
    @Transactional
    public Curriculum addCurriculum(Curriculum curriculum) {
        curriculum.setId(null);

        if (curriculum.isMasterVersion()) {
            List<Curriculum> masterCurriculums = curriculumRepository
                            .findAllCurriculumsByNameAndMasterVersion(
                                            curriculum.getName(), true);

            masterCurriculums.forEach(masterCurriculum -> masterCurriculum
                            .setIsMasterVersion(false));

            curriculumRepository.save(masterCurriculums);
        }

        return curriculumRepository.save(curriculum);
    }

    /**
     * Delete multiple subtopics belonging to the specified curriculum.
     * Subtopics not associated with the curriculum are not deleted.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculumId
     *            The curriculum's ID.
     * @param subtopicIds
     *            The IDs of subtopics to dissociate from the curriculum.
     */
    @Transactional
    public void deleteSubtopics(Integer curriculumId,
                    Iterable<Integer> subtopicIds) {
        curriculumSubtopicRepository
                        .deleteSubtopicsByCurriculumIdAndSubtopicIdIn(
                                        curriculumId, subtopicIds);
    }

    /**
     * Deletes curriculums.<br>
     * <b>WARNING:</b> This will also delete schedules belonging to the
     * curriculum and dissosicate from its subtopics.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculumIds
     *            The IDs of the curriculums to delete.
     */
    @Transactional
    public void deleteCurriculums(Iterable<Integer> curriculumIds) {
        curriculumSubtopicRepository
                        .deleteSubtopicsByCurriculumIdIn(curriculumIds);
        scheduleRepository.deleteSchedulesByCurriculumIdIn(curriculumIds);
        curriculumRepository.deleteCurriculumsByIdIn(curriculumIds);
    }

    /**
     * Gets the specified curriculums.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculumIds
     *            The IDs of the curriculums to acquire.
     * @return The specified curriculums.
     */
    public List<Curriculum> getCurriculums(Set<Integer> curriculumIds) {
        return curriculumRepository.findCurriculumsByIdIn(curriculumIds);
    }

    /**
     * Updates an existing curriculum with the non-{@literal null} fields of
     * {@code curriculum}.
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculum
     *            The curriculum data to update.
     * @return The updated curriculum data.
     * @throws NoContentException
     *             The curriculum being updated doesn't exist.
     */
    @Transactional
    public Curriculum updateCurriculum(Curriculum curriculum)
                    throws NoContentException {
        Curriculum existing = getCurriculumById(curriculum.getId());

        if (existing == null)
            return null;

        ReflectionUtils.deepCopyNonNull(existing, curriculum);

        curriculum = curriculumRepository.save(existing);

        return curriculum;
    }

    /**
     * Replace a curriculum.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param curriculum
     *            The curriculum data to replace an existing curriculum with.
     * @return The new replaced curriculum data.
     * @throws NoContentException
     *             The curriculum to replace doesn't exist.
     */
    @Transactional
    public Curriculum replaceCurriculum(Curriculum curriculum)
                    throws NoContentException {
        Curriculum existing = getCurriculumById(curriculum.getId());

        if (existing == null)
            return null;

        curriculum = curriculumRepository.save(curriculum);

        return curriculum;
    }

    /**
     * Associates subtopics to a curriculum.
     * 
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param id
     *            The ID of the curriculum to associate.
     * @param subtopicIds
     *            The subtopics to associate to the given curriculum.
     * @throws BadRequestException
     *             The curriculum does not exist or there are non-existent
     *             subtopics specified.
     */
    @Transactional
    public void insertSubtopicsToCurriculum(Integer id,
                    Set<Integer> subtopicIds) throws BadRequestException {
        Curriculum curriculum;
        try {
            curriculum = getCurriculumById(id);
        } catch (NoContentException ex) {
            throw new BadRequestException(
                            "Curriculum (id=" + id + ") does not exist.");
        }

        Boolean isValid = remoteTopicService.allSubtopicsExist(subtopicIds);

        if (isValid == null || !isValid)
            throw new BadRequestException(
                            "Non-existent subtopic ids submitted.");

        List<CurriculumSubtopic> curriculumSubtopics = new ArrayList<>(
                        subtopicIds.size());
        Curriculum targetCurriculum = curriculum;

        subtopicIds.forEach(subtopicId -> curriculumSubtopics
                        .add(new CurriculumSubtopic(null, targetCurriculum,
                                        subtopicId)));

        curriculumSubtopicRepository.save(curriculumSubtopics);
    }

    /**
     * Retrieves all schedules belonging to a curriculum.
     * 
     * @author Ricky Baker (1802-Matt)
     * 
     * @param id
     *            The curriculum's ID.
     * @return The schedules associated with the given curriculum.
     */
    public List<Schedule> getAllSchedulesByCurriculumId(Integer id) {
        return scheduleService.getAllSchedulesByCurriculumId(id);
    }
}
