package contact_management.mapper;

import contact_management.dto.NotificationDto;
import contact_management.entity.Notification;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-11T21:29:15+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public List<NotificationDto> toDtos(List<Notification> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<NotificationDto> list = new ArrayList<NotificationDto>( dtos.size() );
        for ( Notification notification : dtos ) {
            list.add( toDto( notification ) );
        }

        return list;
    }

    @Override
    public List<Notification> toBos(List<NotificationDto> bos) {
        if ( bos == null ) {
            return null;
        }

        List<Notification> list = new ArrayList<Notification>( bos.size() );
        for ( NotificationDto notificationDto : bos ) {
            list.add( toBo( notificationDto ) );
        }

        return list;
    }

    @Override
    public NotificationDto toDto(Notification bo) {
        if ( bo == null ) {
            return null;
        }

        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setId( bo.getId() );
        notificationDto.setTitle( bo.getTitle() );
        notificationDto.setMessage( bo.getMessage() );
        notificationDto.setCreateDate( bo.getCreateDate() );
        notificationDto.setSeen( bo.getSeen() );

        return notificationDto;
    }

    @Override
    public Notification toBo(NotificationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Notification notification = new Notification();

        notification.setId( dto.getId() );
        notification.setTitle( dto.getTitle() );
        notification.setMessage( dto.getMessage() );
        notification.setCreateDate( dto.getCreateDate() );
        notification.setSeen( dto.getSeen() );

        return notification;
    }

    @Override
    public Notification fillBo(NotificationDto dto, Notification bo) {
        if ( dto == null ) {
            return null;
        }

        bo.setId( dto.getId() );
        bo.setTitle( dto.getTitle() );
        bo.setMessage( dto.getMessage() );
        bo.setCreateDate( dto.getCreateDate() );
        bo.setSeen( dto.getSeen() );

        return bo;
    }
}
