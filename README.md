# Projet scolaire

La thématique qui sera traitée dans ce travail sera du domaine de la recherche d'emploi. Il sera lieu de faciliter à un demandeur d'emploi une interface d’accès pour voir les offres d'emplois postées et la notification d'offres suivant les catégories concernées.

## Base de données MySQL

Spring crée une base de données appelée "<b>cvdb</b>" et une table appelée "<b>appcv</b>" contenant des colonnes pour stocker des informations sur les candidats à un emploi telles que leur nom, prénom, âge, adresse, spécialité, niveau d'études, expérience professionnelle, adresse e-mail et numéro de téléphone. Des entrées de données ont été insérées dans la table pour simuler des candidats à l'emploi. Enfin, la clé primaire a été définie sur la colonne "id" et cette colonne a été configurée pour s'auto-incrémenter à chaque ajout de nouvelle entrée de données.

## EndPoints

- <b><ins>Save</ins></b>: Il est utilisé pour ajouter une nouvelle entité CV à la base de données.
- <b><ins>All</ins></b>: Il permet de récupérer toutes les données disponibles dans la table de la base de données.
- <b><ins>Update</ins></b>: Il est utilisé pour mettre à jour une entité CV existant dans la base de données en utilisant son identifiant unique (ID). Cet endpoint permet de modifier les propriétés ou les champs d'une entité sans avoir à supprimer et à recréer l'entité.
- <b><ins>Delete</ins></b>: Il permet de supprimer une entité CV ou un objet spécifique à partir de la base de données en utilisant son identifiant unique (ID). Lorsque cet endpoint est appelé, l'objet associé à l'ID spécifié est supprimé de la base de données.
- <b><ins>FindById</ins></b>: Il  est utilisé pour récupérer une entité CV spécifique à partir d'une base de données en utilisant son identifiant unique (ID).

<p align="center">
  <img src="https://user-images.githubusercontent.com/125821344/234912173-1861fd92-31e4-4bbf-ac18-2d6c7ab61fb9.png"/>
</p>

## Interfaces Androids
### Interface de démarrage
<p align="center">
  <img src="https://user-images.githubusercontent.com/125821344/234916121-629eaada-ac42-4c05-ba12-742167d5bf15.png"/>
</p>

Sur cette première interface, nous avons une description de l'application et un bouton '<b>LISTE DES CVS</b>' qui permet d'afficher la liste des CVs.
### Liste des CVs
<p align="center">
  <img src="https://user-images.githubusercontent.com/125821344/234916262-296029d7-eaa2-4c49-8a46-0705d6005190.png"/>
</p>

Cette interface affiche la liste des CVs avec les attributs Prénom, Nom, Email, Spécialité et Niveau d'étude.
On pourra par la suite appuyer sur le bouton '<b>AJOUTER UN CV</b>' pour enregistrer un nouveau CV ou appuyer sur un CV pour en obtenir les détails.
### Enregistrement
En appuyant sur '<b>AJOUTER UN CV</b>', on aura cette interface.
<p align="center">
  <img src="https://user-images.githubusercontent.com/125821344/234916396-833a744b-fe19-45df-a8c0-964f02e89b70.png"/>
</p>

A la suite de l'enregistrement, une alerte nous confirmera l'enregistrement du CV.
### Détails
En appuyant sur un CV, on aura cette interface.
<p align="center">
  <img src="https://user-images.githubusercontent.com/125821344/234916529-115d16c6-3586-46e3-85c8-0c313b01b047.png"/>
</p>

Sur cette interface, nous aurons toutes les informations concernant le CV. Par la suite on pourra :
- Modifier ces informations
- Suppimer le CV
### Mise à jour
En appuyant sur '<b>MODIFIER</b>', on aura cette interface.
<p align="center">
  <img src="https://user-images.githubusercontent.com/125821344/234917196-bf6168ce-e1c2-4805-9db3-d89c1925fa9b.png"/>
</p>

Suite à la modification d'un CV, une alerte nous confirmera que la mise à jour a été effectuer avec succès.
<p align="center">
  <img src="https://user-images.githubusercontent.com/125821344/234917969-c3fb51c2-573f-4369-88f5-57aed2ce5e42.png"/>
</p>

### Suppression
En appuyant sur '<b>SUPPRIMER</b>', on aura cette interface qui nous demandera de confirmer notre choix.
<p align="center">
  <img src="https://user-images.githubusercontent.com/125821344/234917994-2339622f-be08-48be-88c4-ca2bf3881327.png"/>
</p>

<p align="center">
  <img src="https://user-images.githubusercontent.com/125821344/234935036-7f8f76cf-3d35-4a39-9d0a-faa8efefdd63.png"/>
</p>

A la suite de la suppression d'un CV, nous serons renvoyés à la liste des CVs.
<p align="center">
  <img src="https://user-images.githubusercontent.com/125821344/234935248-1f0f5cf7-201d-4da0-84a2-683a59467d71.png"/>
</p>

## Authors
[@Amsata DIAGNE](https://github.com/Amsa-21)
