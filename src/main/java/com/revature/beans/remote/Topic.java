package com.revature.beans.remote;

import com.revature.util.ReflectionUtils;

/**
 * A remote bean mirroring the Topic bean in hydra-topic-service.
 * 
 * <br>
 * <br>
 * <b>Last Modified:</b>
 *  <pre style="margin:0;border:0;padding:0;">    15 April 2018</pre>
 * 
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 * 
 * @version 2.0
 */
public class Topic {
    private Integer topicID;
    private String topicName;
    
    public Topic() {
        super();
    }

    public Topic(String topicName) {
        super();
        this.topicName = topicName;
    }

    public Integer getTopicID() {
        return topicID;
    }


    public void setTopicID(Integer topicID) {
        this.topicID = topicID;
    }


    public String getTopicName() {
        return topicName;
    }


    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }


    @Override
    public String toString() {
        return "TopicName [\n" +"(Topic ID) \t topicID = " + topicID + ",\n "
                + "(Topic name) \t topicName = " + topicName + "]";
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + topicID;
        result = PRIME * result + ((topicName == null) ? 0 : topicName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return ReflectionUtils.testEquality(this, obj);
    }
    
    
}