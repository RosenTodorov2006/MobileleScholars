package bg.soft_uni.mobilelelele.models.dtos;
import java.util.List;

public class BrandExportDto {
    private String name;
    private List<ModelDto> modelDtoList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelDto> getModelDtoList() {
        return modelDtoList;
    }

    public void setModelDtoList(List<ModelDto> modelDtoList) {
        this.modelDtoList = modelDtoList;
    }
}
