package github.tanishqtrivedi27.userService.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.tanishqtrivedi27.userService.models.UserInfoDTO;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class UserInfoDeserializer implements Deserializer<UserInfoDTO> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public UserInfoDTO deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfoDTO userInfoDTO = null;
        try {
            userInfoDTO = objectMapper.readValue(bytes, UserInfoDTO.class);
        } catch (Exception e) {
            System.out.println("Can not deserialize user info" + e.getMessage());
        }
        return userInfoDTO;
    }

    @Override
    public UserInfoDTO deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public UserInfoDTO deserialize(String topic, Headers headers, ByteBuffer data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
