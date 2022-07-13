package com.springapp.mvc.config;

//
//@Component
public class FileStore {

//
//    String fileDir = "/upload/";
//
//    public String getFullPath(String filename) {
//        return fileDir + filename;
//    }
//
//    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
//        List<UploadFile> storeFileResult = new ArrayList<>();
//        for (MultipartFile multipartFile : multipartFiles) {
//            if (!multipartFile.isEmpty()) {
//                storeFileResult.add(storeFile(multipartFile));
//            }
//        }
//        return storeFileResult;
//    }
//
//    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
//        if (multipartFile.isEmpty()) {
//            return null;
//        }
//
//        // ex) image.png
//        String originalFilename = multipartFile.getOriginalFilename();
//
//        // 서버에 저장하는 파일명
//        String storeFileName = createStoreFileName(originalFilename);
//        multipartFile.transferTo(new File(getFullPath(storeFileName)));
//
//        return new UploadFile(originalFilename, storeFileName);
//    }
//
//    private String createStoreFileName(String originalFilename) {
//        String ext = extractExt(originalFilename);
//        String uuid = UUID.randomUUID().toString();
//        return uuid + "." + ext;
//    }
//
//    private String extractExt(String originalFilename) {
//        int pos = originalFilename.lastIndexOf(".");
//        return originalFilename.substring(pos + 1);
//    }
}