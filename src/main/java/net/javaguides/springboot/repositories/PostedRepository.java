package net.javaguides.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.javaguides.springboot.model.Post;
import net.javaguides.springboot.model.Posted;

//Do not implement this interface.
//Spring data will create an implementation of it with @Repository
@Repository
public interface PostedRepository extends JpaRepository<Post, Long>{


	
}
