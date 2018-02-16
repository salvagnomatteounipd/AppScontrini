package database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;

/**
 * Represents one person that does some missions
 * @author Marco Olivieri(Team 3)
 */

@Entity(tableName = Constants.PERSON_TABLE_NAME)
@TypeConverters(Converters.class) // automatic converters for database correct type

public class PersonEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.PERSON_PRIMARY_KEY)
    private long ID;
    private String name;
    private String lastName;
    private String academicTitle;



    private Uri foto;


    private String email;

    @Ignore
    /**
     * Non parametric constructor to use when you don't want set all fields
     */
    public PersonEntity() {
    }

    /**
     * Parametric constructor
     *
     * @param name Name of the person
     * @param lastName Last Name of the person
     * @param academicTitle Academic Title of the person
     */
    public PersonEntity(String name, String lastName, String academicTitle) {
        this.name = name;
        this.lastName = lastName;
        this.academicTitle = academicTitle;

    }

    /**
     * Parametric constructor
     *
     * @param name Name of the person
     * @param lastName Last Name of the person
     * @param academicTitle Academic Title of the person
     * @param email Person's email
     * @param foto Uri of Person's foto
     */
    @Ignore
    public PersonEntity(String name, String lastName, String academicTitle, String email, Uri foto) {
        this.name = name;
        this.lastName = lastName;
        this.academicTitle = academicTitle;
        this.email = email;
        this.foto = foto;
    }

    /**
     * Returns the PersonEntity ID
     * @return ID
     */
    public long getID() {
        return ID;
    }

    @Deprecated
    /** This method should no longer exists, since the ID is auto-generated by the database
     * Sets person id
     * @param ID not null
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    /**
     * Returns person's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets person's name
     * @param name not null
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns person's last name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets person's last name
     * @param lastName not null
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns person's academic title
     * @return academicTitle
     */
    public String getAcademicTitle() {
        return academicTitle;
    }

    /**
     * Sets person's academic title
     * @param academicTitle not null
     */
    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    /**
     * Returns person's email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets person's email
     * @param email not null
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns person's foto
     * @return foto, Uri
     */
    public Uri getFoto() {
        return foto;
    }

    /**
     * Sets person's foto
     * @param foto, Uri not null
     */
    public void setFoto(Uri foto) {
        this.foto = foto;
    }

}
