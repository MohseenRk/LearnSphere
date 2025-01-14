package com.learnSphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnSphere.entities.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer>{

	Lesson findByLessonId(int lessonId);

}
