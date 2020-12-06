package communicationWithFirstTier;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import shared.Artwork;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArtworkModelAssembler implements RepresentationModelAssembler<Artwork, EntityModel<Artwork>>
{
  @Override public EntityModel<Artwork> toModel(Artwork entity)
  {
    return EntityModel.of(entity,
        linkTo(methodOn(ArtworkController.class).one(entity)).withSelfRel(),
        linkTo(methodOn(ArtworkController.class).editOne(entity)).withSelfRel(),
        linkTo(methodOn(ArtworkController.class).all()).withRel("artworks"));
  }
}
