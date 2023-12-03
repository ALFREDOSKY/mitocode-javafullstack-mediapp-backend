package com.mitocode.controller;

import com.mitocode.dto.PatientDTO;
import com.mitocode.dto.VitalSignDTO;
import com.mitocode.model.Patient;
import com.mitocode.model.VitalSign;
import com.mitocode.service.IVitalSignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/vitalsigns")
@RequiredArgsConstructor
public class VitalSignController {

    private final IVitalSignService service;

    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<VitalSignDTO>> findAll(){
        List<VitalSignDTO> lst = service.findAll().stream().map(this::convertToDto).toList();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalSignDTO> findById(@PathVariable("id") Integer id){
        VitalSign obj = service.findById(id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VitalSignDTO> save(@Valid @RequestBody VitalSignDTO dto){
        VitalSign obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVitalSign()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VitalSignDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody VitalSignDTO dto) throws Exception {
        dto.setIdVitalSign(id);
        VitalSign obj = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<VitalSignDTO> findByIdHateoas(@PathVariable("id") Integer id){
        EntityModel<VitalSignDTO> resource = EntityModel.of(convertToDto(service.findById(id)));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));

        resource.add(link1.withRel("vitalsign-info1"));
        resource.add(link1.withRel("vitalsign-info2"));
        return resource;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<VitalSignDTO>> listPage(Pageable pageable){
        Page<VitalSignDTO> page = service.listPage(pageable).map(p -> mapper.map(p, VitalSignDTO.class));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    private VitalSignDTO convertToDto(VitalSign obj){
        return mapper.map(obj, VitalSignDTO.class);
    }

    private VitalSign convertToEntity(VitalSignDTO dto){
        return mapper.map(dto, VitalSign.class);
    }

}
