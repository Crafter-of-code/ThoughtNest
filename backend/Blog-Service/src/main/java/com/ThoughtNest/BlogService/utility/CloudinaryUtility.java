package com.ThoughtNest.BlogService.utility;

import com.ThoughtNest.BlogService.dto.BlogCoverImageDto;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CloudinaryUtility {
    private final Cloudinary cloudinary;
    public BlogCoverImageDto uploadCoverImage(BlogCoverImageDto imageData){
        try{
            Map uploadUrl = cloudinary.uploader().upload(imageData.getCoverImage().getBytes(), ObjectUtils.emptyMap());

            String secureUrl = uploadUrl.get("secure_url").toString();
            String publicUrl = uploadUrl.get("public_id").toString();
            imageData.setSecureUrl(secureUrl);
            imageData.setPublicUrl(publicUrl);
        }catch (Exception e){
            System.out.println(e.getMessage());
            imageData.setSecureUrl(null);
            imageData.setPublicUrl(null);
        }
        imageData.setCoverImage(null);
        return  imageData;
    }
}
