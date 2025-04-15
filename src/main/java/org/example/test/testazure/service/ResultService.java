package org.example.test.testazure.service;

import org.example.test.testazure.request.SubmitRequest;

import java.util.List;
import java.util.Map;

public interface ResultService {
    double calculatePoint(List<SubmitRequest> data);
    List<Integer> correctAnswer(List<SubmitRequest> data);
}
