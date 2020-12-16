package communicationWithFirstTier;

import logic.offers.ArtworksLogic;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import shared.Artwork;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArtworkController
{
    ArtworkModelAssembler assembler;
    ArtworksLogic logic;

    public ArtworkController(ArtworkModelAssembler assembler, ArtworksLogic logic)
    {
        this.assembler = assembler;
        this.logic = logic;
    }

    @PostMapping("/addArtwork")
    EntityModel<Artwork> oneArtwork(@RequestBody Artwork artwork)
    {
        Artwork newArtwork = logic.saveArtwork(artwork);
        return assembler.toModel(newArtwork);
    }

    /*  @GetMapping("/artworks")
      CollectionModel<EntityModel<Artwork>> all()
      {
        List<EntityModel<Artwork>> artworks = logic.getAll().stream().map(assembler::toModel).collect(
            Collectors.toList());
        return CollectionModel.of(artworks,linkTo(methodOn(ArtworkController.class).all()).withSelfRel());
      }*/
    @GetMapping("/artworks")
    List<EntityModel<Artwork>> allArtworks()
    {
        return logic.getAllArtworks().stream().map(assembler::toModel).collect(
                Collectors.toList());
    }

    @GetMapping("/artworks/{id}")
    EntityModel<Artwork> oneArtwork(@PathVariable int id)
    {
        Artwork artwork = logic.getArtwork(id);
        return assembler.toModel(artwork);
    }
    @DeleteMapping("/artworks/{id}")
    void deleteArtwork(@PathVariable int id)
    {
        logic.deleteArtwork(id);
    }

    @PutMapping("/editArtwork")
    EntityModel<Artwork> editOneArtwork(@RequestBody Artwork editedArtwork)
    {
        Artwork artwork = logic.editArtwork(editedArtwork);
        return assembler.toModel(artwork);
    }
}
