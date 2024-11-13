package br.com.project.backend.uni.modal.emuns;

public enum GalleryType {

    UNI_VELHA_CENTRAL("uni_velha_central"),
    UNI_TODOS("uni_todos"),
    CONFERENCIAS("conferencias");

    private String type;

    GalleryType(String type) {
        this.type = type;
    }
}
