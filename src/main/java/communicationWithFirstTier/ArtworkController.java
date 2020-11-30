package communicationWithFirstTier;

import logic.offers.ArtworksLogic;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shared.Artwork;

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
}
