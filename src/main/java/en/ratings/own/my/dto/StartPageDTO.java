package en.ratings.own.my.dto;

import en.ratings.own.my.dto.rating.RatingForStartPageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StartPageDTO {

    private UserDTO UserDTO;

    private ArrayList<RatingForStartPageDTO> ratingDTOs;
}
