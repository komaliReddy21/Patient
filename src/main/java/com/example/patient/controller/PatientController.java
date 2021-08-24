package com.example.patient.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.patient.entity.Patient;
import com.example.patient.repo.PatientRepository;

@RestController("/")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;

	@PostMapping("patient")
	public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
		try {
			Patient _patient = patientRepository.save(patient);
			return new ResponseEntity<>(_patient, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("patient/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
		try {
			Patient _patient = patientRepository.findById(id).get();
			return new ResponseEntity<>(_patient, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("patient")
	public ResponseEntity<List<Patient>> getPatients() {
		try {
			List<Patient> _patients = patientRepository.findAll();
			return new ResponseEntity<>(_patients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("patient/{id}")
	public ResponseEntity<Patient> updatePatientById(@PathVariable("id") Long id, @RequestBody Patient patient) {
		try {
			Optional<Patient> _patientOp = patientRepository.findById(id);
			if(_patientOp.isPresent()) {
				Patient _patient = _patientOp.get();
				_patient.setAddress(patient.getAddress());
				_patient.setCity(patient.getCity());
				_patient.setEmail(patient.getEmail());
				_patient.setFirstName(patient.getFirstName());
				_patient.setLastName(patient.getLastName());
				_patient.setPhoneNumber(patient.getPhoneNumber());
				_patient.setState(patient.getState());
				_patient.setZipCode(patient.getZipCode());

				return new ResponseEntity<>(patientRepository.save(_patient), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(patientRepository.save(patient), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("patient/{id}")
	public ResponseEntity<Patient> deletePatientById(@PathVariable("id") Long id) {
		try {
			patientRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("patient")
	public ResponseEntity<Patient> deletePatients() {
		try {
			patientRepository.deleteAll();;
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
