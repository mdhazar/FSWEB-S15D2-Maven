package org.example.entity;

import java.util.HashSet;
import java.util.Set;

public class TaskData {
    private Set<Task> annsTasks;
    private Set<Task> bobsTasks;
    private Set<Task> carolsTasks;
    private Set<Task> unassignedTasks;

    public TaskData(Set<Task> annsTasks, Set<Task> bobsTasks, Set<Task> carolsTasks, Set<Task> unassignedTasks) {
        this.annsTasks = annsTasks != null ? annsTasks : new HashSet<>();
        this.bobsTasks = bobsTasks != null ? bobsTasks : new HashSet<>();
        this.carolsTasks = carolsTasks != null? carolsTasks : new HashSet<>();
        this.unassignedTasks = unassignedTasks != null ? unassignedTasks: new HashSet<>();
    }
    public Set<Task> getTasks(String assignee){
        switch (assignee.toLowerCase()){
            case "ann":
                return annsTasks;
            case "bob":
                return bobsTasks;
            case "carol":
                return carolsTasks;
            case "all":
                Set<Task> annBobUnion = getUnion(annsTasks, bobsTasks);
                Set<Task> annBobCarolUnion = getUnion(annBobUnion, carolsTasks);
                return getUnion(annBobCarolUnion, unassignedTasks);
            default:
                return new HashSet<>();
        }
    }

    public Set<Task> getUnion(Set<Task> set1, Set<Task> set2) {
        Set<Task> result = new HashSet<>();
        result.addAll(set1);
        result.addAll(set2);
        return result;
    }
    public Set<Task> getIntersection (Set<Task> set1,Set<Task> set2){
        Set<Task> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        return intersection;
    }
    public Set<Task> getDifferences(Set<Task> set1, Set<Task> set2){
        Set<Task> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        return difference;
    }
    public Set<Task> getUnassignedTasks(){
        return new HashSet<>(unassignedTasks);
    }

    public Set<Task> getTasksAssignedToMultipleEmployees(){
        Set<Task> annBobIntersect = getIntersection(annsTasks,bobsTasks);
        Set<Task> annCarolIntersect = getIntersection(annsTasks,carolsTasks);
        Set<Task> bobCarolIntersect = getIntersection(bobsTasks,carolsTasks);
        Set<Task> result = new HashSet<>();
        result.addAll(annBobIntersect);
        result.addAll(annCarolIntersect);
        result.addAll(bobCarolIntersect);

        return result;
    }

}