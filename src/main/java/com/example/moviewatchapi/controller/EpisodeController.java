/*
package com.example.moviewatchapi.controller;


        import com.example.moviewatchapi.dto.EpisodeDTO;
        import com.example.moviewatchapi.service.EpisodeService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/episodes")
public class EpisodeController {

    private EpisodeService episodeService;

    public EpisodeController(
            EpisodeService episodeService
    ){
        this.episodeService = episodeService;
    }
    // Method to get all episodes
    @GetMapping
    public ResponseEntity<List<EpisodeDTO>> getAllEpisodes() {
        List<EpisodeDTO> episodes = episodeService.getAllEpisodes();
        return new ResponseEntity<>(episodes, HttpStatus.OK);
    }

    // Method to get an episode by id
    @GetMapping("/{id}")
    public ResponseEntity<EpisodeDTO> getEpisodeById(@PathVariable Integer id) {
        EpisodeDTO episode = episodeService.getEpisodeById(id);
        return new ResponseEntity<>(episode, HttpStatus.OK);
    }

    // Method to save an episode
    @PostMapping
    public ResponseEntity<EpisodeDTO> saveEpisode(@RequestBody EpisodeDTO episodeDTO) {
        EpisodeDTO episode = episodeService.saveEpisode(episodeDTO);
        return new ResponseEntity<>(episode, HttpStatus.CREATED);
    }

    // Method to update an episode
    @PutMapping("/{id}")
    public ResponseEntity<EpisodeDTO> updateEpisode(@PathVariable Integer id, @RequestBody EpisodeDTO episodeDTO) {
        EpisodeDTO episode = episodeService.updateEpisode(id, episodeDTO);
        return new ResponseEntity<>(episode, HttpStatus.OK);
    }

    // Method to delete an episode
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable Integer id) {
        episodeService.deleteEpisode(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
*/
