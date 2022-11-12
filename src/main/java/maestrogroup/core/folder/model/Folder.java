package maestrogroup.core.folder.model;

import lombok.Data;

// folderIdx, folderImgUrl, folderName, teamIdx

@Data
public class Folder {
    private int folderIdx;
    private String folderImgUrl;
    private String folderName;
    private int teamIdx;
}
