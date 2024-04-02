package com.training.ems.services;

import com.training.ems.dto.StatsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticsService {
    List<StatsDto> getStats();
}
