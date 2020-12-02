package logic.offers;

import communicationWithFirstTier.ArtworkException;
import communicationWithThirdTier.Communicator;
import shared.Artwork;

import java.util.List;

public class ArtworksLogic
{
  Communicator communicator= Communicator.getInstance();

  public ArtworksLogic() throws Exception
  {
  }

  public Artwork saveArtwork(Artwork artwork)
  {
    Artwork savedArtwork;
    try{
      savedArtwork = communicator.saveArtworkInDb(artwork);
    }
    catch(Exception e){
      e.printStackTrace();
      throw new ArtworkException("Server error");
    }
    if(savedArtwork!= null)
    {
      return savedArtwork;
    }
    throw new ArtworkException("Could not save offer");
  }

  public List<Artwork> getAll()
  {
    try{
      return communicator.getAllArtworks();
    }
    catch(Exception e){
      e.printStackTrace();
      throw new ArtworkException("Server error");
    }
  }
}
