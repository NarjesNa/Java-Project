# Java Project

Purpose : Visualize metabolic Pathways using KEGG informations (KGML files) 
Generating graphs showing the reactions, compounds and enzymes involved in a metabolic pathway in a dynamic way 


We used Graphstream library to generate graphs : http://graphstream-project.org/ 

Informations : 
You need to execute the main function to launch the application. 
You need to download Bacteria.zip and Reaction.zip files to run the programm

Bacteria.zip : this file contains the KGML files for each specie : KGML file contains all the informations related to a pathway (name, reactions and compounds involved)
Reaction.zip : this file contains all the informations for a reaction, name, type of reaction (reversible or irreversible), code of the enzyme that catalyzes the reaction. You need these informations to orientate your graphs
