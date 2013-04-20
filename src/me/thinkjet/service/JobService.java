package me.thinkjet.service;

import me.thinkjet.model.Job;

public class JobService {
public void createJob(Job job){
	job.save();
}
public void update(Job job){
	job.update();
}
}
