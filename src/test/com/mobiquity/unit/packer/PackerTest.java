package com.mobiquity.unit.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PackerTest {

    @Mock
    private Packer packer;

    @Test
    public void testVerifyPack() throws APIException, IOException {
        when(packer.pack(anyString())).thenReturn("");

        packer.pack("");

        verify(packer, times(1))
                .pack("");
    }
}
