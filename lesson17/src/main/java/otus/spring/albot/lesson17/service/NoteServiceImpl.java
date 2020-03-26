package otus.spring.albot.lesson17.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson17.dto.NoteDto;
import otus.spring.albot.lesson17.entity.Product;
import otus.spring.albot.lesson17.exception.NoSuchProductException;
import otus.spring.albot.lesson17.repo.NoteRepo;
import otus.spring.albot.lesson17.repo.ProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private ProductRepo productRepo;
    private NoteRepo noteRepo;

    @Override
    @Transactional
    public List<NoteDto> getNotesToProduct(long productId) throws NoSuchProductException {
        Product product = productRepo.findById(productId).orElseThrow(() -> new NoSuchProductException(productId));
        return product.getNotes().stream().map(NoteDto::fromDao).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NoteDto addNote(NoteDto noteDto) {
        return NoteDto.fromDao(noteRepo.save(NoteDto.toDao(noteDto)));
    }

    @Override
    @Transactional
    public void removeNote(long id) {
        noteRepo.deleteById(id);
    }
}
