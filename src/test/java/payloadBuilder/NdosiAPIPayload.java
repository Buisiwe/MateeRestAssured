package payloadBuilder;

import org.json.simple.JSONObject;

public class NdosiAPIPayload {
    public static JSONObject registerUserPayload() {

        JSONObject registerUser = new JSONObject();
        registerUser.put("firstName","John");
        registerUser.put("lastName","Doe");
        registerUser.put("email","22bee@gmail.com");
        registerUser.put("password","SecurePass123@");
        registerUser.put("confirmPassword","SecurePass123@");
        registerUser.put("groupId","5328c91e-fc40-11f0-8e00-5000e6331276");
        return registerUser;
    }

    public static JSONObject loginUserPayload() {

        JSONObject loginUser = new JSONObject();
        loginUser.put("email","bee@gmail.com");
        loginUser.put("password","SecurePass123@");

        return loginUser;
    }
    public static JSONObject createTestimonialPayload(){
        JSONObject testimonial = new JSONObject();
        testimonial.put("title","Great Service!");
        testimonial.put("content","This is my testimonial content describing the excellent service I received");
        testimonial.put("rating",5);
        testimonial.put("IsPublic",true);

        return testimonial;
    }

    public static JSONObject updateUserProfilePayload(){
        JSONObject updateUserProfile = new JSONObject();
        updateUserProfile.put("full_name","Busi Matee");
        return updateUserProfile;
    }

    public static JSONObject updateTestimonialPayload(){
        JSONObject updateTestimonial = new JSONObject();
        updateTestimonial.put("title","Updated Testimonial Title");
        updateTestimonial.put("content","Updated testimonial content");
        updateTestimonial.put("rating",4);

        return updateTestimonial;
    }
}
