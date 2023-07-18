package ru.mcclinics.orderservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.mcclinics.orderservice.dto.AuthorDto;
import ru.mcclinics.orderservice.dto.ModuleDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Table(name = "series")
@Entity
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "series_id")
    private Long id;
    @Column(name = "seria_name")
    private String seriesName;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    private String annotation;
    @Column(name = "create_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "series")
    @JsonManagedReference(value="series-lecture")
    private List<Lecture> lectures;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    @JsonBackReference(value="track-series")
    private Track track;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "series")
    private List<KeyWord> keyWords;
    @Transient
    private Long frontEndId;

    public Series() {
    }

    public Series(String seriesName, Author author, String annotation, Track track) {
        this.seriesName = seriesName;
        this.author = author;
        this.annotation = annotation;
        this.track = track;
    }
    public Series(ModuleDto moduleDto) {
        this.seriesName= moduleDto.getModuleNameModal();
        this.annotation = moduleDto.getModuleModalAnnotation();
        String[] strMain = moduleDto.getModuleModalKeyWords().split(";");
        List<KeyWord> keyWords = new ArrayList<>(strMain.length);
        for (int i = 0; i < strMain.length; i++){
            KeyWord keyWord = new KeyWord();
            keyWord.setValue(strMain[i]);
            keyWords.add(keyWord);
        }
        this.keyWords = keyWords;
        this.frontEndId = Long.valueOf(moduleDto.getId());
    }
}
