package pro.sky.telegrambot.models;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Arrays;
import java.util.Objects;

public class DataAnimalPhotoInputOutput {
    private String idPhoto;
    private String idAnimal;
    private String description;
    private byte[] content;


    public DataAnimalPhotoInputOutput() {
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataAnimalPhotoInputOutput that = (DataAnimalPhotoInputOutput) o;
        return idPhoto.equals(that.idPhoto) && description.equals(that.description) && Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idPhoto, description);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @Override
    public String toString() {
        return "DataAnimalPhotoInputOutput{" +
                "idPhoto='" + idPhoto + '\'' +
                ", description='" + description + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }

    public String getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }
}
