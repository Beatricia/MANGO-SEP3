# Locally 
~ Not an average third semester project

Welcome to <b>Locally</b>, third semester project by the group <b>MANGO</b>.
- <b>M</b>ayer Simon
- <b>A</b>gata Rudol
- <b>N</b>agit Beatricia
- <b>G</b>ergo Nador
- <b>O</b>uafa Hammoun


## Setup

1. Fork the project (or download it as a zip)

2. Setting up Database

This project uses PostgreSQL database, therefore the <b>PostgreSQL database has to be installed</b> on the target machine. If the previous step is done, <b>create a new schema called 'locally'</b> (without the apostrophe). (If you wish to name it something else, then there is one more step in the point 3). After the schema is created, make sure the PostgreSQL <b>database is running</b>.

3. Setting up Data Tier

The Data Tier is connecting the logic to the database, so make sure the database is running (previous steps). Also, this tier was written in Java 17, so <b>make sure your machine has Java 17 (or newer) installed</b>.
<br>
Next, navigate to the DataTier/DatabaseAccess/src/main/resources folder, copy the 'application.properties.example' file, and rename the copy to 'application.properties'. Open the renamed copy with any text editor program. In the line 7, change the username value to your postgresql username (by default this is postgres, so if you have the same username, you can skip this step). Next, in the line 8, type your postgresql password associated with the previously given username. Furthermore, if the schema created in the database has a different name than 'locally', then in the line 6 change the currentSchema=locally to the name of the schema you want to use. (e.g. currentSchema=myLocallySchema).
<br>
Last step is to open cmd, navigate to the DataTier/DatabaseAccess folder, and type 'mvnw clean install'. 
<br><br>
If you followed the previous steps, the Data tier should be ready to use.

4. Setting up Logic Tier

To set up the Logic tier, open a new cmd console (or use the previous one), navigate to the folder LogicTier/, and run the command 'dotnet build'. The Logic tier is done.

5. Setting up Presentation Tier

Setting up this tier is very similar to the previous one. Navigate a cmd console to the PresentationTier/ folder, and run the command 'dotnet build'. Everything is set up! Good work. Now lets run the projects.

## Run

- To run the Data Tier, navigate a cmd console to the DataTier/DatabaseAccess folder, and run the command 'java -jar target/DatabaseAccess-0.0.1-SNAPSHOT.jar'. The spring framework should start up.
- To run the Logic Tier, run this cmd command in the LogicTier/WebAPI folder: 'dotnet run'
- To run the Presentation Tier, run the following cmd command in the PresentationTier/BlazorLocally folder: 'dotnet run'

Congrats! You have now set up the Locally project and ran all the different tiers.
