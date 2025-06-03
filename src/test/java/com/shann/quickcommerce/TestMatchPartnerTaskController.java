package com.shann.quickcommerce;

import com.shann.quickcommerce.controllers.MatchPartnerTaskController;
import com.shann.quickcommerce.dtos.MatchPartnerTaskRequestDto;
import com.shann.quickcommerce.dtos.MatchPartnerTaskResponseDto;
import com.shann.quickcommerce.dtos.ResponseStatus;
import com.shann.quickcommerce.entities.Location;
import com.shann.quickcommerce.entities.Partner;
import com.shann.quickcommerce.entities.PartnerTaskMapping;
import com.shann.quickcommerce.entities.PickUpTask;
import com.shann.quickcommerce.repositories.PartnerRepository;
import com.shann.quickcommerce.repositories.PickUpTaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestMatchPartnerTaskController {

    @Autowired
    private PickUpTaskRepository taskRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private MatchPartnerTaskController matchPartnerTaskController;

    private List<PickUpTask> tasks;
    private List<Partner> partners;

    @BeforeEach
    public void insertDummyData(){
        PickUpTask task1 = new PickUpTask();
        task1.setCustomerId(1L);
        task1.setPickupLocation(new Location(10, 10));

        PickUpTask task2 = new PickUpTask();
        task2.setCustomerId(2L);
        task2.setPickupLocation(new Location(20, 20));

        PickUpTask task3 = new PickUpTask();
        task3.setCustomerId(3L);
        task3.setPickupLocation(new Location(30, 30));

        Partner partner1 = new Partner();
        //partner1.setId(1L);
        partner1.setPartnerName("Partner 1");
        partner1.setPartnerStatus(com.shann.quickcommerce.enums.PartnerStatus.AVAILABLE);
        partner1.setCurrentLocation(new Location(11, 11));

        Partner partner2 = new Partner();
        //partner2.setId(2L);
        partner2.setPartnerName("Partner 2");
        partner2.setPartnerStatus(com.shann.quickcommerce.enums.PartnerStatus.AVAILABLE);
        partner2.setCurrentLocation(new Location(21, 21));

        Partner partner3 = new Partner();
        //partner3.setId(3L);
        partner3.setPartnerName("Partner 3");
        partner3.setPartnerStatus(com.shann.quickcommerce.enums.PartnerStatus.AVAILABLE);
        partner3.setCurrentLocation(new Location(31, 31));


        task1 = taskRepository.save(task1);
        task2 = taskRepository.save(task2);
        task3 = taskRepository.save(task3);
        tasks = Arrays.asList(task1, task2, task3);

        partner1 = partnerRepository.save(partner1);
        partner2 = partnerRepository.save(partner2);
        partner3 = partnerRepository.save(partner3);
        partners = Arrays.asList(partner1, partner2, partner3);

        System.out.println("Dummy data, partners: " + partners + ", tasks: " + tasks);
    }

    @AfterEach
    public void cleanUp(){
        taskRepository.deleteAll();
        partnerRepository.deleteAll();
    }

    @Test
    public void testMatchPartnerTask_3_Partners_3_Tasks(){
        MatchPartnerTaskRequestDto requestDto = new MatchPartnerTaskRequestDto();
        requestDto.setPartnerIds(partners.stream().map(Partner::getId).toList());
        requestDto.setTaskIds(tasks.stream().map(PickUpTask::getId).toList());

        MatchPartnerTaskResponseDto matchPartnerTaskResponseDto = matchPartnerTaskController.matchPartnersAndTasks(requestDto);
        assertNotNull(matchPartnerTaskResponseDto, "Response should be not null");
        assertEquals(ResponseStatus.SUCCESS, matchPartnerTaskResponseDto.getResponseStatus(), "Response status should be SUCCESS");
        assertEquals(3, matchPartnerTaskResponseDto.getPartnerTaskMappings().size(), "There should be 3 partner task mappings");
        List<PartnerTaskMapping> partnerTaskMappings = matchPartnerTaskResponseDto.getPartnerTaskMappings();
        for(PartnerTaskMapping partnerTaskMapping: partnerTaskMappings){
            Location partnerLocation = partnerTaskMapping.getPartner().getCurrentLocation();
            Location taskLocation = partnerTaskMapping.getTask().getPickupLocation();
            int partnerQuotient =(int) partnerLocation.getLatitude() / 10;
            int taskQuotient = (int) taskLocation.getLatitude() / 10;
            assertEquals(partnerQuotient, taskQuotient, String.format("Partner with id %d should be matched with task with id %d, take a look at the dummy data for more details", partnerTaskMapping.getPartner().getId(), partnerTaskMapping.getTask().getId()));

        }
    }

    @Test
    public void testMatchPartnerTask_3_Partners_2_Tasks(){
        MatchPartnerTaskRequestDto requestDto = new MatchPartnerTaskRequestDto();
        requestDto.setPartnerIds(partners.stream().map(Partner::getId).toList());
        requestDto.setTaskIds(tasks.stream().limit(2).map(PickUpTask::getId).toList());

        MatchPartnerTaskResponseDto matchPartnerTaskResponseDto = matchPartnerTaskController.matchPartnersAndTasks(requestDto);
        assertNotNull(matchPartnerTaskResponseDto, "Response should be not null");
        assertEquals(ResponseStatus.SUCCESS, matchPartnerTaskResponseDto.getResponseStatus(), "Response status should be SUCCESS");
        assertEquals(2, matchPartnerTaskResponseDto.getPartnerTaskMappings().size(), "There should be 3 partner task mappings");
        List<PartnerTaskMapping> partnerTaskMappings = matchPartnerTaskResponseDto.getPartnerTaskMappings();
        for(PartnerTaskMapping partnerTaskMapping: partnerTaskMappings){
            Location partnerLocation = partnerTaskMapping.getPartner().getCurrentLocation();
            Location taskLocation = partnerTaskMapping.getTask().getPickupLocation();
            int partnerQuotient =(int) partnerLocation.getLatitude() / 10;
            int taskQuotient = (int) taskLocation.getLatitude() / 10;
            assertEquals(partnerQuotient, taskQuotient, String.format("Partner with id %d should be matched with task with id %d, take a look at the dummy data for more details", partnerTaskMapping.getPartner().getId(), partnerTaskMapping.getTask().getId()));

        }
    }

    @Test
    public void testMatchPartnerTask_2_Partners_3_Tasks(){
        MatchPartnerTaskRequestDto requestDto = new MatchPartnerTaskRequestDto();
        requestDto.setPartnerIds(partners.stream().limit(2).filter(partner -> partner.getId() != 3L).map(Partner::getId).toList());
        requestDto.setTaskIds(tasks.stream().map(PickUpTask::getId).toList());

        MatchPartnerTaskResponseDto matchPartnerTaskResponseDto = matchPartnerTaskController.matchPartnersAndTasks(requestDto);
        assertNotNull(matchPartnerTaskResponseDto, "Response should be not null");
        assertEquals(ResponseStatus.SUCCESS, matchPartnerTaskResponseDto.getResponseStatus(), "Response status should be SUCCESS");
        assertEquals(2, matchPartnerTaskResponseDto.getPartnerTaskMappings().size(), "There should be 3 partner task mappings");
        List<PartnerTaskMapping> partnerTaskMappings = matchPartnerTaskResponseDto.getPartnerTaskMappings();
        for(PartnerTaskMapping partnerTaskMapping: partnerTaskMappings){
            Location partnerLocation = partnerTaskMapping.getPartner().getCurrentLocation();
            Location taskLocation = partnerTaskMapping.getTask().getPickupLocation();
            int partnerQuotient =(int) partnerLocation.getLatitude() / 10;
            int taskQuotient = (int) taskLocation.getLatitude() / 10;
            assertEquals(partnerQuotient, taskQuotient, String.format("Partner with id %d should be matched with task with id %d, take a look at the dummy data for more details", partnerTaskMapping.getPartner().getId(), partnerTaskMapping.getTask().getId()));

        }
    }


}
