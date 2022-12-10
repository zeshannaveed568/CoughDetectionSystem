package com.example.pulmonarydisease.Adapter;

import android.widget.EditText;
import android.widget.Spinner;

public class Questionnaire {

    String spinDiseases, spinSmoke,spinIndustrial,spinCovid, editWeight,editPhysicalActivities,editSurgery,editGeneticDisease, editAllergies, editSleep;

    public Questionnaire() {
    }

    public Questionnaire(String spinDiseases, String spinSmoke, String spinIndustrial, String spinCovid, String editWeight, String editPhysicalActivities, String editSurgery, String editGeneticDisease, String editAllergies, String editSleep) {
        this.spinDiseases = spinDiseases;
        this.spinSmoke = spinSmoke;
        this.spinIndustrial = spinIndustrial;
        this.spinCovid = spinCovid;
        this.editWeight = editWeight;
        this.editPhysicalActivities = editPhysicalActivities;
        this.editSurgery = editSurgery;
        this.editGeneticDisease = editGeneticDisease;
        this.editAllergies = editAllergies;
        this.editSleep = editSleep;
    }

    public String getSpinDiseases() {
        return spinDiseases;
    }

    public void setSpinDiseases(String spinDiseases) {
        this.spinDiseases = spinDiseases;
    }

    public String getSpinSmoke() {
        return spinSmoke;
    }

    public void setSpinSmoke(String spinSmoke) {
        this.spinSmoke = spinSmoke;
    }

    public String getSpinIndustrial() {
        return spinIndustrial;
    }

    public void setSpinIndustrial(String spinIndustrial) {
        this.spinIndustrial = spinIndustrial;
    }

    public String getSpinCovid() {
        return spinCovid;
    }

    public void setSpinCovid(String spinCovid) {
        this.spinCovid = spinCovid;
    }

    public String getEditWeight() {
        return editWeight;
    }

    public void setEditWeight(String editWeight) {
        this.editWeight = editWeight;
    }

    public String getEditPhysicalActivities() {
        return editPhysicalActivities;
    }

    public void setEditPhysicalActivities(String editPhysicalActivities) {
        this.editPhysicalActivities = editPhysicalActivities;
    }

    public String getEditSurgery() {
        return editSurgery;
    }

    public void setEditSurgery(String editSurgery) {
        this.editSurgery = editSurgery;
    }

    public String getEditGeneticDisease() {
        return editGeneticDisease;
    }

    public void setEditGeneticDisease(String editGeneticDisease) {
        this.editGeneticDisease = editGeneticDisease;
    }

    public String getEditAllergies() {
        return editAllergies;
    }

    public void setEditAllergies(String editAllergies) {
        this.editAllergies = editAllergies;
    }

    public String getEditSleep() {
        return editSleep;
    }

    public void setEditSleep(String editSleep) {
        this.editSleep = editSleep;
    }
}
