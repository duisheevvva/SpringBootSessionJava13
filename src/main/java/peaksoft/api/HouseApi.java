package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.MyException;
import peaksoft.entity.House;
import peaksoft.service.HouseService;

import java.util.List;

@Controller
@RequestMapping("/houses/{agencyId}")
@RequiredArgsConstructor
public class HouseApi {

    private final HouseService houseService;


    @GetMapping()
    public String getAllHouse(@PathVariable("agencyId") Long agencyId, Model model) {
        model.addAttribute("houses", houseService.getAllHouse(agencyId));
        model.addAttribute("id", agencyId);
        System.out.println("agency id : " + agencyId);
        return "house/houseMainPage";
    }

    @GetMapping("/new")
    public String createHouse(@PathVariable Long agencyId, Model model) {
        model.addAttribute("agencyId", agencyId);
        model.addAttribute("house", new House());
        model.addAttribute("houseType", new String[]{"APARTMENT", "COTTAGE", "CASTLE", "VILLA"});
        return "house/newHouse";
    }


    @PostMapping("/save")
    public String saveHouse(@PathVariable Long agencyId,
                            @ModelAttribute("house") House house) {
        houseService.saveHouse(agencyId, house);
        return "redirect:/houses/" + agencyId;
    }


        @PostMapping("/{houseId}/edit")
    public String updateHouse(@PathVariable("agencyId") Long id, Model model,@PathVariable Long agencyId) throws MyException {
      model.addAttribute("house",houseService.getById(id));
      model.addAttribute("agencyId",agencyId);
      return "house/updateHouse";

    }
    @GetMapping("/{houseId}/edit")
    public String edit(@PathVariable Long houseId, Model model, @PathVariable Long agencyId) throws MyException {
        model.addAttribute("house", houseService.getById(houseId));
        model.addAttribute("houseTypes", new String[]{"APARTMENT", "COTTAGE", "CASTLE", "VILLA"});
        model.addAttribute(agencyId);
        return "house/updateHouse";
    }

    @PostMapping("/{houseId}/update")
    public String saveUpdate(@ModelAttribute("house") House house,
                             @PathVariable("houseId") Long id,
                             @PathVariable("agencyId") Long agencyId) {
        houseService.updateHouseById(id, house);
        return "redirect:/houses/" + agencyId;
    }

    @PostMapping("/{houseId}/delete")
    public String delete(@PathVariable Long houseId, @PathVariable("agencyId") Long agencyId) {
        houseService.deleteHouseById(houseId);
        return "redirect:/houses/" + agencyId;
    }

    @GetMapping("/sort")
    public List<House> sortHouseByHouseType(@RequestParam String order, @PathVariable String agencyId) {
        return houseService.sortHouseByHouseType(order);
    }


//    @GetMapping("/sort")
//    public String sortHouseByHouseType(@RequestParam String order, Model model) {
//        List<House> houses = houseService.sortHouseByHouseType(order);
//        model.addAttribute("houses", houses);
//        return "houseSortResults";
//    }
//
    @GetMapping("/search/{agencyId}")
    public String searchHouse(@RequestParam String word, Model model, @PathVariable Long agencyId) {
        House house= houseService.searchHouse(word);
        model.addAttribute("houses", house);
        return "house/houseSearchResult";
    }

}




