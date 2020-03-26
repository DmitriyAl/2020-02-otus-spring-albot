package otus.spring.albot.lesson17.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import otus.spring.albot.lesson17.dto.NoteDto;
import otus.spring.albot.lesson17.entity.Product;
import otus.spring.albot.lesson17.exception.NoSuchProductException;
import otus.spring.albot.lesson17.repo.ProductRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private ProductRepo productRepo;

    @Override
    public List<NoteDto> getNotesToProduct(long productId) throws NoSuchProductException {
        Product product = productRepo.findById(productId).orElseThrow(() -> new NoSuchProductException(productId));
        return product.getNotes().stream().map(NoteDto::fromDao).collect(Collectors.toList());
    }
}
