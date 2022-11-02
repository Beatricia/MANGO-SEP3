package mango.sep3.databaseaccess.FileData;

import mango.sep3.databaseaccess.protobuf.Farm;
import mango.sep3.databaseaccess.protobuf.Offer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class FileContext
{
  private final String filePath = "data.json";
  private DataContainer dataContainer;

  public FileContext()
  {
    try
    {
      LoadData();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    dataContainer = new DataContainer();
  }


  public Collection<Farm> Farms()
  {
    try
    {
      LoadData();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return dataContainer.getFarms();
  }

  public Collection<Offer> Offers()
  {
    try
    {
      LoadData();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return dataContainer.getOffers();
  }

  //Taking the data from the file
  public void LoadData() throws FileNotFoundException
  {
    if (dataContainer != null)
      return;

    // Deserializing the string file into a DataContainer
    try( ObjectInputStream objectInputStream =  new ObjectInputStream(new FileInputStream(filePath)))
    {
      dataContainer = (DataContainer) objectInputStream.readObject();
    } catch (Exception e){
      //if the file does not exist it creates a new data container
      dataContainer = new DataContainer()
      {{
        setFarms(new ArrayList<>());
        setOffers(new ArrayList<>());
      }};
    }
  }

  //Saving the changes to the file
  public void SaveChanges(){
    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
    )
    {
      //writing it to the data container
      objectOutputStream.writeObject(dataContainer);
    } catch (Exception e){

    }
  }

  //TODO Two methods to get the Farms and the Offers



}
