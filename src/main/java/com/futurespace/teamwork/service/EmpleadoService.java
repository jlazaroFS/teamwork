package com.futurespace.teamwork.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

import com.futurespace.teamwork.models.Empleado;
import com.futurespace.teamwork.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {
    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public List<Empleado> getAllEmpleado() {
        return repository.findAll();
    }

    public Empleado addEmpleado(Empleado e) {
        // Validation checks
        if (!isValidNif(e.getTxNif())) {
            throw new IllegalArgumentException("El NIF proporcionado no es válido");
        }
        if (!isValidEmail(e.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico proporcionado no es válido");
        }
        if (!isValidEdocivil(e.getCxEdocivil())) {
            throw new IllegalArgumentException("El estado civil debe ser soltero (\"S\") o casado (\"C\")");
        }
        if (!isValidServmilitar(e.getbServmilitar())) {
            throw new IllegalArgumentException("Se debe indicar si ha cumplido servicio militar (\"S\") o no (\"N\")");
        }
        if (e.getfNacimiento().after(Date.valueOf(LocalDate.now().minusYears(18)))) {
            throw new IllegalArgumentException("Todos los empleados deben ser mayores de edad");
        }

        // Unicity tests
        if (repository.existsByTxNif(e.getTxNif())) {
            throw new IllegalArgumentException("Ya existe un empleado con el mismo NIF");
        }
        if (repository.existsByEmail(e.getEmail())) {
            throw new IllegalArgumentException("Ya existe un empleado con el mismo correo electrónico");
        }

        return repository.save(e);
    }

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    /**
     * Checks that the input email is valid using a regular expresion.
     * 
     * @param email The email address that needs validation.
     * @return True if the address is valid, False otherwise.
     */
    private boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    private static final String DNI_REGEX = "^\\d{8}[A-Z]$";
    private static final String NIE_REGEX = "^[XYZ]\\d{7}[A-Z]$";
    private static final String CONTROL_DIGITS = "TRWAGMYFPDXBNJZSQVHLCKE";

    /**
     * Checks if the input NIF (DNI or NIE) is valid, including the control digit.
     * 
     * @param inputNif The NIF that needs validation.
     * @return True if the NIF is valid, False otherwise.
     */
    public boolean isValidNif(String inputNif) {
        if (inputNif == null || inputNif.isEmpty()) {
            return false;
        }

        String nif = inputNif;

        if (nif.matches(NIE_REGEX)) {
            nif = decodeNiePrefix(nif);
        }

        if (nif.matches(DNI_REGEX)) {
            int number = Integer.parseInt(nif.substring(0, 8));
            char expectedControlDigit = CONTROL_DIGITS.charAt(number % 23);
            char actualControlDigit = nif.charAt(8);
            return actualControlDigit == expectedControlDigit;
        }

        return false;
    }

    /**
     * Decodes the first letter in the NIE to make it compatible with DNI validation
     * rules
     * 
     * @param nie The NIE, starts with 'X', 'Y' or 'Z', contains 7 digits and ends
     *            with a capital letter.
     * @return A DNI-like ID, equivalent (validation-wise) to the input NIE.
     */
    private String decodeNiePrefix(String nie) {
        switch (nie.charAt(0)) {
            case 'X':
                return "0" + nie.substring(1);
            case 'Y':
                return "1" + nie.substring(1);
            case 'Z':
                return "2" + nie.substring(1);
            default:
                throw new IllegalArgumentException("NIE no válido");
        }
    }

    private boolean isValidEdocivil(Character cxEdocivil) {
        return (cxEdocivil == 'S' || cxEdocivil == 'C');
    }

    private boolean isValidServmilitar(Character bServmilitar) {
        return (bServmilitar == 'S' || bServmilitar == 'N');
    }

    public Empleado modifyFbajaEmpleado(Long idEmpleado, Date newFbaja) {
        Empleado e = repository.findById(idEmpleado).orElseThrow(
                () -> new IllegalArgumentException("No se ha encontrado ningún empleado con ID: " + idEmpleado));

        if (newFbaja.before(e.getfAlta())) {
            throw new IllegalArgumentException("La fecha de baja debe ser posterior a la fecha de alta");
        }

        Date today = Date.valueOf(LocalDate.now());

        if (newFbaja.after(today)) {
            throw new IllegalArgumentException("La fecha de baja no puede ser posterior a la fecha de hoy");
        }

        e.setfBaja(newFbaja);
        return repository.save(e);
    }
}
