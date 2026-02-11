package payloadBuilder;

import org.json.simple.JSONObject;

public class TestimonialsPayload {
    public static JSONObject registerUserPayload() {

        JSONObject registerUser = new JSONObject();
        registerUser.put("firstname","John");
        registerUser.put("lastname","Doe");
        registerUser.put("email"," mpydf5dm1z@example.com");
        registerUser.put("password","SecurePass123");
        registerUser.put("confirmPassword","SecurePass123");
        return registerUser;
    }

    public static JSONObject loginUserPayload() {

        JSONObject loginUser = new JSONObject();
        loginUser.put("email","mpydf5dm1z@example.com");
        loginUser.put("password","SecurePass123");

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

    public static JSONObject updateUserProfilePayload(String token,String email){
        JSONObject updateUserProfile = new JSONObject();
        updateUserProfile.put("full_name","Busi Matee");
        return updateUserProfile;
    }

    public static JSONObject updateTestimonialPayload(String user_Id){
        JSONObject updateTestimonial = new JSONObject();
        updateTestimonial.put("userid",user_Id);
        updateTestimonial.put("content","Updated testimonial content");
        updateTestimonial.put("rating",4);

        return updateTestimonial;
    }
}
