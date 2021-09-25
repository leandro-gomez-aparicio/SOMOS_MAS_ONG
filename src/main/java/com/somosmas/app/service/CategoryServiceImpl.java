package com.somosmas.app.service;

import com.somosmas.app.exception.custom.CategoryAlreadyExistException;
import com.somosmas.app.model.entity.Category;
import com.somosmas.app.model.request.CategoryRequest;
import com.somosmas.app.model.response.CategoryResponse;
import com.somosmas.app.model.response.ListCategoryResponse;
import com.somosmas.app.repository.ICategoryRepository;
import com.somosmas.app.service.abstraction.ICategoryService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.TimestampUtil;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final String CATEGORY_ID_NOT_FOUND = "Category ID: {0} not found.";

    @Autowired
    private ICategoryRepository categoryRepository;
    
    @Autowired
    ModelMapper modelMapper;

    @Override
    public void delete(Long id) throws NoSuchElementException {
        Category category = getCategory(id);
        category.setSoftDelete(true);
        categoryRepository.save(category);
    }

    @Override
	@Transactional(readOnly = true)
	public Page<ListCategoryResponse> listCategoryNames(Integer page) throws NotFoundException {
		ConvertUtil modelToDto= new ConvertUtil();
		Pageable pagina = PageRequest.of(page, 2);
		   Page<Category> categories = categoryRepository.findBySoftDeleteIsNullOrSoftDeleteIsFalse(pagina);
		   if(categories.getTotalPages() <= pagina.getPageNumber() || categories.isEmpty() ){
		      throw new NotFoundException();
		   }
	        return categories.map(this::modelToDTO);	 
	}
	
    private ListCategoryResponse modelToDTO(Category category) {
        return modelMapper.map(category, ListCategoryResponse.class);
    }

    @Override
    public CategoryResponse update(CategoryRequest categoryRequest, Long id) throws NoSuchElementException {
        getCategory(id);

        Category category = ConvertUtil.convertToEntity(categoryRequest);
        category.setIdCategory(id);
        category = categoryRepository.save(category);
        return ConvertUtil.convertToDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse findBy(Long id) {
        Category category = getCategory(id);
        return ConvertUtil.convertToDto(category);
    }

    @Override
    public void create(CategoryRequest categoryRequest) throws CategoryAlreadyExistException {
        if ((categoryRepository.findByName(categoryRequest.getName()).isPresent())) {
            throw new CategoryAlreadyExistException(categoryRequest.getName());
        }
        Category category = ConvertUtil.convertToEntity(categoryRequest);
        category.setTimeStamp(TimestampUtil.getCurrentTime());
        category.setSoftDelete(false);
        categoryRepository.save(category);
    }

    private Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(CATEGORY_ID_NOT_FOUND, id)));
    }
}
