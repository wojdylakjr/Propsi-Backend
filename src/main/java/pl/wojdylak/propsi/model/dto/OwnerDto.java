package pl.wojdylak.propsi.model.dto;

import pl.wojdylak.propsi.model.Owner;

public class OwnerDto {
    private Long id;

    private String name;

    public OwnerDto() {
    }

    public OwnerDto(Owner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OwnerDto{" +
                "id=" + id +
                ", firstName='" + name + '\'' +
                '}';
    }
}
