package cbnu.campingmaster.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDto {
    private String memberId;
    private String memberPw;
}
