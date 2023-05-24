//package ru.mcclinics.orderservice.view;
//
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.grid.Grid;
//
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.data.value.ValueChangeMode;
//import com.vaadin.flow.router.Route;
//import org.springframework.beans.factory.annotation.Autowired;
//import ru.mcclinics.orderservice.components.TrackEditor;
//import ru.mcclinics.orderservice.dao.TrackRepository;
//import ru.mcclinics.orderservice.domain.Track;
//
//@Route
//public class MainView extends VerticalLayout{
//    private final TrackRepository TrackRepository;
//    private Grid<Track> grid = new Grid<>(Track.class);
//    private final TextField filter = new TextField("", "Type to filter");
//    private final Button addNewBtn = new Button("Add new");
//    private HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn);
//    private final TrackEditor editor;
//    @Autowired
//    public MainView(TrackRepository TrackRepository, TrackEditor editor) {
//        this.TrackRepository = TrackRepository;
//        this.editor = editor;
//
//        add(toolbar, grid, editor);
//
//        filter.setValueChangeMode(ValueChangeMode.EAGER);
//        filter.addValueChangeListener(e -> showTracks(e.getValue()));
//
//        grid.asSingleSelect().addValueChangeListener(e -> {
//            editor.editTrack(e.getValue());
//        });
//
//        addNewBtn.addClickListener(e -> editor.editTrack(new Track()));
//
//        editor.setChangeHandler(() -> {
//            editor.setVisible(false);
//            showTracks(filter.getValue());
//        });
//        showTracks("");
//    }
//
//    private void showTracks(String name) {
//        if (name.isEmpty()){
//            grid.setItems(TrackRepository.findAll());
//        } else {
//            grid.setItems(TrackRepository.findByTrackNameStartsWithIgnoreCase(name));
//        }
//
//    }
//
//
//}
