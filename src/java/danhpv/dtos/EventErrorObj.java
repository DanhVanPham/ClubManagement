/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhpv.dtos;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class EventErrorObj implements Serializable{
    private String eventIDError, eventNameError, eventDescError, locatorError, timeCloseRegisError, timeStartEventError, timeCloseEventError;
    private String totalError, internalError;

    public EventErrorObj() {
    }


    public String getEventIDError() {
        return eventIDError;
    }

    public void setEventIDError(String eventIDError) {
        this.eventIDError = eventIDError;
    }

    public String getEventNameError() {
        return eventNameError;
    }

    public void setEventNameError(String eventNameError) {
        this.eventNameError = eventNameError;
    }

    public String getEventDescError() {
        return eventDescError;
    }

    public void setEventDescError(String eventDescError) {
        this.eventDescError = eventDescError;
    }

    public String getLocatorError() {
        return locatorError;
    }

    public void setLocatorError(String locatorError) {
        this.locatorError = locatorError;
    }

    public String getTimeCloseRegisError() {
        return timeCloseRegisError;
    }

    public void setTimeCloseRegisError(String timeCloseRegisError) {
        this.timeCloseRegisError = timeCloseRegisError;
    }

    public String getTimeStartEventError() {
        return timeStartEventError;
    }

    public void setTimeStartEventError(String timeStartEventError) {
        this.timeStartEventError = timeStartEventError;
    }

    public String getTimeCloseEventError() {
        return timeCloseEventError;
    }

    public void setTimeCloseEventError(String timeCloseEventError) {
        this.timeCloseEventError = timeCloseEventError;
    }

    public String getTotalError() {
        return totalError;
    }

    public void setTotalError(String totalError) {
        this.totalError = totalError;
    }

    public String getInternalError() {
        return internalError;
    }

    public void setInternalError(String internalError) {
        this.internalError = internalError;
    }

    
    
}
