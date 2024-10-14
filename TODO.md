AuthServiceConsumer::Listen
Todo: Make it transactional, to handle idempotency and validate email, phoneNumber etc

see update user logic
UnaryOperator<UserInfo> updateUser = user -> userRepository.save(userInfoDTO.transform());
