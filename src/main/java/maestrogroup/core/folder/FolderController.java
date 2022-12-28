package maestrogroup.core.folder;

import maestrogroup.core.folder.model.Folder;
import maestrogroup.core.folder.model.*;
import maestrogroup.core.folder.model.PostFolderReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://maestro:3000")
public class FolderController {

    @Autowired
    private final FolderProvider folderProvider;
    @Autowired
    private final FolderService folderService;
    @Autowired
    private final FolderDao folderDao;

    public FolderController(FolderProvider folderProvider, FolderService folderService, FolderDao folderDao) {
        this.folderProvider = folderProvider;
        this.folderService = folderService;
        this.folderDao = folderDao;
    }

    // POST: /create_folder/{teamID}
    // folderIdx, folderImgUrl, folderName, teamIdx
    @ResponseBody
    @PostMapping("/create_folder/{teamIdx}")
    public void createFolder(@PathVariable("teamIdx") int teamIdx, @RequestBody PostFolderReq postFolderReq){
        folderService.createFolder(postFolderReq, teamIdx);
    }

    // 특정 팀에 대한 폴더 리스트 조회
    @ResponseBody
    @GetMapping("/get_all_folder/{teamIdx}")
    public List<Folder> GetAllFolder(@PathVariable("teamIdx") int teamIdx){
        return folderProvider.GetAllFolder(teamIdx);
    }

    // 폴더 수정 : 그런데 만약에 인자값이 1개만 들어온다면? ModifyFolderReq 의 생성자를 어떻게 선언하고 처리하는가?
    // => 인자 1개만 받으면 안되니까, 인자를 반드시 2개 모두 입력받기 위한 검증 및 에외처리 필요함
    // ex) 폴더 이미지 필드값에 아무것도 저장이 안된 상태(= NULL 값이 저장된 상태)로 요청이 들어오면 다시 입력하라고 Response를 보내자.
    // 또한 폴더 수정페이지로 넘어갔을떄, 기본적으로 폴더 이미지와 폴더 이름 수정란에 아무것도 값이 없는것이 아닌, 디폴트 값으로 기존에
    // 해당 폴더에 저장되어있던 값들을 수정란에 띄어주는 것으로 가정
    @ResponseBody
    @PatchMapping("/patchFolder/{folderIdx}")
    public void modifyFolder(@PathVariable("folderIdx") int folderIdx, @RequestBody ModifyFolderReq modifyFolderReq){
        folderService.modifyFolder(folderIdx, modifyFolderReq);
    }

    // 폴더를 삭제할떄 폴더안에 담겨있는 음악 객체들도 모두 삭제되도록 API 추가 개발 처리요망
    @ResponseBody
    @DeleteMapping("/deleteFolder/{folderIdx}")
    public void deleteFolder(@PathVariable("folderIdx") int folderIdx){
        folderService.deleteFolder(folderIdx);
    }
}
