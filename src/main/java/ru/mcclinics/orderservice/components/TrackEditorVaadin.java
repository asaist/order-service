//package ru.mcclinics.orderservice.components;
//
//import com.vaadin.flow.component.Key;
//import com.vaadin.flow.component.KeyNotifier;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.icon.VaadinIcon;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.binder.Binder;
//import com.vaadin.flow.spring.annotation.SpringComponent;
//import com.vaadin.flow.spring.annotation.UIScope;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import ru.mcclinics.orderservice.dao.AuthorRepository;
//import ru.mcclinics.orderservice.dao.TrackRepository;
//import ru.mcclinics.orderservice.domain.Author;
//import ru.mcclinics.orderservice.domain.Track;
//import ru.mcclinics.orderservice.domain.TrackStatus;
//
//@SpringComponent
//@UIScope
//public class TrackEditor extends VerticalLayout implements KeyNotifier {
//    private final TrackRepository TrackRepository;
//    private final AuthorRepository authorRepository;
//    private Track Track;
//    TextField trackName = new TextField("Track name");
//    TextField annotation = new TextField("annotation");
//    ComboBox<TrackStatus> expertGroupStatus = new ComboBox<>("expertGroupStatus");
//    ComboBox<Author> author = new ComboBox<>("author");
//    private Button save = new Button("Save", VaadinIcon.CHECK.create());
//    private Button cancel = new Button("Cancel");
//    private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
//    private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
//    private Binder<Track> binder = new Binder<>(Track.class);
//    @Setter
//    private ChangeHandler changeHandler;
//
//
//    public interface ChangeHandler {
//        void onChange();
//    }
//    @Autowired
//    public TrackEditor(TrackRepository TrackRepository, AuthorRepository authorRepository) {
//        this.TrackRepository = TrackRepository;
//        this.authorRepository = authorRepository;
//        add(trackName, annotation, author, expertGroupStatus, actions);
//        binder.bindInstanceFields(this);
//        setSpacing(true);
//
//        save.getElement().getThemeList().add("primary");
//        delete.getElement().getThemeList().add("error");
//
//        addKeyPressListener(Key.ENTER, e -> save());
//
//        // wire action buttons to save, delete and reset
//        save.addClickListener(e -> save());
//        delete.addClickListener(e -> delete());
//        cancel.addClickListener(e -> editTrack(Track));
//        setVisible(false);
//    }
//
//    private void save() {
//        TrackRepository.save(Track);
//        changeHandler.onChange();
//    }
//    private void delete() {
//        TrackRepository.delete(Track);
//        changeHandler.onChange();
//    }
//    public void editTrack(Track newTrack) {
//        if (newTrack == null) {
//            setVisible(false);
//            return;
//        }
//        if (newTrack.getId() != null) {
//            Track = TrackRepository.findById(newTrack.getId()).get();
//        } else {
//            Track = newTrack;
//        }
//        binder.setBean(Track);
//        setVisible(true);
//        trackName.focus();
//    }
//}
