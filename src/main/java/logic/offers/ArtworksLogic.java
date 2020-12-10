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
  public Artwork editArtwork(Artwork artwork)
  {
    Artwork editedArtwork;
    try{
      editedArtwork = communicator.editArtwork(artwork);
    }
    catch(Exception e){
      e.printStackTrace();
      throw new ArtworkException("Server error");
    }
    if(editedArtwork!= null)
    {
      return editedArtwork;
    }
    throw new ArtworkException("Could not edit offer");
  }

  public void deleteArtwork(int id)
  {
    communicator.deleteArtwork(id);
  }
  public List<Artwork> getAllArtworks()
  {
    try{
      return communicator.getAllArtworks();
    }
    catch(Exception e){
      e.printStackTrace();
      throw new ArtworkException("Server error");
    }
  }
  public Artwork getArtwork(int id)
  {
    Artwork artwork;
    try{
      artwork = communicator.getArtwork(id);
    }
    catch (Exception e)
    {
      throw new ArtworkException("Server error, could not load this offer");
    }
    if(artwork!=null)
    {
      return artwork;
    }
    throw  new ArtworkException("This offer no longer exist");
  }

}
