package pe.com.yourclothes.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.yourclothes.backend.entities.catalogue.*;
import pe.com.yourclothes.backend.repositories.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CatalogueController {

    @Autowired
    ConditionRepository conditionRepository;
    @Autowired
    GenderRepository genderRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    PricetypeRepository pricetypeRepository;
    @Autowired
    SeasonRepository seasonRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    TypeRepository typeRepository;

    @GetMapping("/conditions")
    public ResponseEntity<List<Condition>> getConditions()
    {
        List<Condition> conditions = conditionRepository.findAll();
        return new ResponseEntity<List<Condition>>(conditions, HttpStatus.OK);
    }
    @GetMapping("/genders")
    public ResponseEntity<List<Gender>> getGenders()
    {
        List<Gender> genders = genderRepository.findAll();
        return new ResponseEntity<List<Gender>>(genders, HttpStatus.OK);
    }
    @GetMapping("/materials")
    public ResponseEntity<List<Material>> getMaterials()
    {
        List<Material> materials = materialRepository.findAll();
        return new ResponseEntity<List<Material>>(materials, HttpStatus.OK);
    }
    @GetMapping("/pricetypes")
    public ResponseEntity<List<Pricetype>> getPricetypes()
    {
        List<Pricetype> pricetypes = pricetypeRepository.findAll();
        return new ResponseEntity<List<Pricetype>>(pricetypes, HttpStatus.OK);
    }
    @GetMapping("/seasons")
    public ResponseEntity<List<Season>> getSeasons()
    {
        List<Season> seasons = seasonRepository.findAll();
        return new ResponseEntity<List<Season>>(seasons, HttpStatus.OK);
    }
    @GetMapping("/sizes")
    public ResponseEntity<List<Size>> getSizes()
    {
        List<Size> sizes = sizeRepository.findAll();
        return new ResponseEntity<List<Size>>(sizes, HttpStatus.OK);
    }
    @GetMapping("/types")
    public ResponseEntity<List<Type>> getTypes()
    {
        List<Type> types = typeRepository.findAll();
        return new ResponseEntity<List<Type>>(types, HttpStatus.OK);
    }

}
