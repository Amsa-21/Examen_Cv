package sn.esmt.emploi.httpConfig;

public class CVsResponse {
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String adresse;
    private String email;
    private String telephone;
    private String specialite;
    private String niveauEtude;
    private String experienceProfessionnelle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    public String getExperienceProfessionnelle() {
        return experienceProfessionnelle;
    }

    public void setExperienceProfessionnelle(String experienceProfessionnelle) {
        this.experienceProfessionnelle = experienceProfessionnelle;
    }

    public CVsResponse(int id, String nom, String prenom, int age, String adresse, String email,
                       String telephone, String specialite, String niveauEtude, String experienceProfessionnelle) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.specialite = specialite;
        this.niveauEtude = niveauEtude;
        this.experienceProfessionnelle = experienceProfessionnelle;
    }

    public CVsResponse() {
    }

}
