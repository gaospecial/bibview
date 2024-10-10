import com.ruoyi.common.ZoteroApiClient;
import com.ruoyi.system.mapper.BibMapper;
import com.ruoyi.system.service.IBibService;
import com.ruoyi.system.service.impl.BibServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ZoteroApiClient.class})
public class BibServiceImplTest {

    private IBibService bibService;

    @Before
    public void setUp() {
        BibMapper mockBibMapper = Mockito.mock(BibMapper.class);
        bibService = new BibServiceImpl(mockBibMapper);
    }

    @Test
    public void testSyncBib() throws IOException {
            bibService.syncBib();
    }

}
