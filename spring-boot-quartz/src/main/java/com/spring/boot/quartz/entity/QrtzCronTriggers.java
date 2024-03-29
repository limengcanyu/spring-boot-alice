package com.spring.boot.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author rock
 * @since 2022-08-17
 */
@TableName("QRTZ_CRON_TRIGGERS")
public class QrtzCronTriggers implements Serializable {

    private static final long serialVersionUID = 1L;

    private String schedName;

    private String triggerName;

    private String triggerGroup;

    private String cronExpression;

    private String timeZoneId;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }
    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @Override
    public String toString() {
        return "QrtzCronTriggers{" +
            "schedName=" + schedName +
            ", triggerName=" + triggerName +
            ", triggerGroup=" + triggerGroup +
            ", cronExpression=" + cronExpression +
            ", timeZoneId=" + timeZoneId +
        "}";
    }
}
