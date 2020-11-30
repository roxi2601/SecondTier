package shared;

import javax.persistence.Entity;

@Entity
public class Artwork
{
  private byte[] pictureBytes;
  private String title;
  private String description;
  private String author;
  private int price;
  private int userId;
  private int id;
  private String category;

  public Artwork(byte[] pictureBytes, String title, String description,
      String author, int price, int userId, int id, String category)
  {
    this.pictureBytes = pictureBytes;
    this.title = title;
    this.description = description;
    this.author = author;
    this.price = price;
    this.userId = userId;
    this.id = id;
    this.category = category;
  }

  public Artwork(ArtworkDTO dto)
  {
    this(dto.getPictureBytes(),dto.getTitle(),dto.getDescription(),dto.getAuthor(),dto.getPrice(),dto.getUserId(),dto.getId(),dto.getCategory());
  }
  public byte[] getPictureBytes()
  {
    return pictureBytes;
  }

  public void setPictureBytes(byte[] pictureBytes)
  {
    this.pictureBytes = pictureBytes;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getAuthor()
  {
    return author;
  }

  public void setAuthor(String author)
  {
    this.author = author;
  }

  public int getPrice()
  {
    return price;
  }

  public void setPrice(int price)
  {
    this.price = price;
  }

  public int getUserId()
  {
    return userId;
  }

  public void setUserId(int userId)
  {
    this.userId = userId;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getCategory()
  {
    return category;
  }

  public void setCategory(String category)
  {
    this.category = category;
  }
}
