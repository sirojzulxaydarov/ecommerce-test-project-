package org.example.online_ecommerce;

import org.example.online_ecommerce.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepo extends JpaRepository<Attachment, UUID> {
}