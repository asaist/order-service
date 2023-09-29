package ru.mcclinics.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mcclinics.orderservice.dao.ShapeRepository;
import ru.mcclinics.orderservice.domain.Shape;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShapeService {
    private final ShapeRepository shapeRepository;
    public List<Shape> findShapes(){return shapeRepository.findAll();}
}
