package communicationWithFirstTier;

import logic.offers.ArtworksLogic;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import shared.Artwork;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
  EntityModel<Artwork> one(@RequestBody Artwork artwork)
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
List<EntityModel<Artwork>> all()
{
  return logic.getAll().stream().map(assembler::toModel).collect(
      Collectors.toList());
}

@GetMapping("/artworks/{id}")
    EntityModel<Artwork> one(@PathVariable int id)
{
  Artwork artwork = logic.get(id);
  return assembler.toModel(artwork);
}
@DeleteMapping("/artworks/{id}")
  void  delete(@PathVariable int id)
{
  logic.deleteArtwork(id);
}
}
